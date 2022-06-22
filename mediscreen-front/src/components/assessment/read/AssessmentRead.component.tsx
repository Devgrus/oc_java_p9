import React, {useEffect, useState} from 'react';
import {Button, Modal} from "react-bootstrap";

export type AssessmentData = {
    family: string;
    given: string;
    age: number;
    sex: string;
    diabetesAssessment: string;
}

type AssessmentReadProps = {
    id: string | undefined;
}

const AssessmentRead = ({id}: AssessmentReadProps) => {
    const [show, setShow] = useState(false);
    const [assessment, setAssessment] = useState({} as AssessmentData);
    useEffect(() => {
        getAssessment();
    }, [])

    const handleClose = () => {
        setShow(false);
    };
    const handleShow = () => setShow(true);

    const getAssessment = (): void => {
        console.log("Get analyse result");
        fetch("http://localhost:8083/assess/id/" + id, {
            method: "GET",
        })
            .then(res => res.json())
            .then(res => setAssessment(res))
            .catch(e => console.log(e));
    }

    return (
        <>
            <button className="btn btn-link p-0 text-black text-decoration-none" onClick={handleShow}>
                Diabetes
            </button>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Diabetes assessment</Modal.Title>
                </Modal.Header>
                    <Modal.Body>
                        <p>Family Name: {assessment.family}</p>
                        <p>Given Name: {assessment.given}</p>
                        <p>Sex: {assessment.sex}</p>
                        <p>Age: {assessment.age}</p>
                        <p>Diabetes Risk Level: {assessment.diabetesAssessment}</p>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Close
                        </Button>
                    </Modal.Footer>
            </Modal>
        </>
    )
}

export default AssessmentRead;