import React, {useEffect, useState} from 'react';
import {Button, Form} from "react-bootstrap";
import {useParams} from "react-router-dom";
import {History} from "../../../pages/history/main/HistoryMainView";
import { useNavigate } from "react-router-dom";

type UpdateHistoryData = {
    id: string;
    patId: string;
    note: string;
}

const EditHistory = () => {
    const params = useParams();
    const navigate = useNavigate();
    const [history, setHistory] = useState<History>({} as History);

    useEffect(() => {
        document.title = 'Mediscreen | History';
        getHistory();
    }, []);

    const getHistory = (): void => {
        console.log("Get history info");
        fetch("http://localhost:8082/patHistory?id=" + params.id, {
            method: "GET",
        })
            .then(res => res.json())
            .then(res => setHistory(res))
            .catch(e => console.log(e));
    }

    const updateHistory = async (history:UpdateHistoryData):Promise<Response> => {
        return await fetch("http://localhost:8082/patHistory", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(history)
        });
    }

    const updatePatientHandler = async (event: React.FormEvent<HTMLFormElement>): Promise<void> => {
        event.preventDefault();
        const data:UpdateHistoryData = {
            id: (event.currentTarget.elements.namedItem("id") as HTMLInputElement).value,
            patId: (event.currentTarget.elements.namedItem("patId") as HTMLInputElement).value,
            note: (event.currentTarget.elements.namedItem("note") as HTMLInputElement).value,
        }

        const fetchUpdate = await updateHistory(data);
        if(fetchUpdate.ok) {
            navigate(`/history/${history.patId}`);
        } else {
            const result = await fetchUpdate.json();
            console.log(result);
        }
    }

    return (
        <>
            <Form onSubmit={updatePatientHandler}>
                <Form.Group className="mb-3">
                    <Form.Control name="id" value={history.id} hidden />
                    <Form.Control name="patId" value={history.patId} hidden />
                    <Form.Label>Note</Form.Label>
                    <Form.Control name="note" as="textarea" rows={10} defaultValue={history.note} />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Update
                </Button>
            </Form>
        </>
    )
}

export default EditHistory;