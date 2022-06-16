import React, {useEffect, useState} from 'react';
import {Button, Form} from "react-bootstrap";
import {useLocation, useParams} from "react-router-dom";
import {History} from "../../../pages/history/main/HistoryMainView";
import { useNavigate } from "react-router-dom";

type AddHistoryData = {
    patId: string;
    note: string;
}

interface LocationProps {
    id: number;
}

const AddHistory = () => {
    const params = useParams();
    const navigate = useNavigate();
    const state = useLocation().state as LocationProps;
    const patId = state.id;
    const [history, setHistory] = useState<History>({} as History);

    useEffect(() => {
        document.title = 'Mediscreen | History';
    }, []);

    const updateHistory = async (history:AddHistoryData):Promise<Response> => {
        return await fetch("http://localhost:8082/patHistory/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(history)
        });
    }

    const addPatientHandler = async (event: React.FormEvent<HTMLFormElement>): Promise<void> => {
        event.preventDefault();
        const data:AddHistoryData = {
            patId: (event.currentTarget.elements.namedItem("patId") as HTMLInputElement).value,
            note: (event.currentTarget.elements.namedItem("note") as HTMLInputElement).value,
        }

        const fetchUpdate = await updateHistory(data);
        if(fetchUpdate.ok) {
            navigate(`/history/${patId}`);
        } else {
            const result = await fetchUpdate.json();
            console.log(result);
        }
    }

    return (
        <>
            <Form onSubmit={addPatientHandler}>
                <Form.Group className="mb-3">
                    <Form.Control name="patId" defaultValue={patId} hidden />
                    <Form.Label>Note</Form.Label>
                    <Form.Control name="note" as="textarea" rows={10} />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Add
                </Button>
            </Form>
        </>
    )
}

export default AddHistory;