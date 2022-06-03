import {Button, Form, Modal} from "react-bootstrap";
import React from "react";
import {Patient, PatientError} from "../main/PatientMainView";

type updateProps = {
    show: boolean;
    handleClose: ()=>void;
    patient: Patient;
    patientListUpdateDetector: boolean;
    setPatientListUpdateDetector: (value: boolean)=>void;
    regexValidation: (patient: Patient) => PatientError;
    errorMsg: PatientError;
    setErrorMsg: (value: PatientError)=>void
}

const PatientUpdate = ({show, handleClose, patient, patientListUpdateDetector, setPatientListUpdateDetector, regexValidation, errorMsg, setErrorMsg}: updateProps) => {

    const updatePatientHandler = async (event: React.FormEvent<HTMLFormElement>): Promise<void> => {
        event.preventDefault();
        const patient:Patient = {
            id: (event.currentTarget.elements.namedItem("id") as HTMLInputElement).value,
            family: (event.currentTarget.elements.namedItem("family") as HTMLInputElement).value.toLowerCase(),
            given: (event.currentTarget.elements.namedItem("given") as HTMLInputElement).value.toLowerCase(),
            sex: (event.currentTarget.elements.namedItem("sex") as HTMLInputElement).value.toUpperCase(),
            dob: (event.currentTarget.elements.namedItem("dob") as HTMLInputElement).value,
            address: (event.currentTarget.elements.namedItem("address") as HTMLInputElement).value,
            phone: (event.currentTarget.elements.namedItem("phone") as HTMLInputElement).value,
        }

        const validationResult = regexValidation(patient);
        if(Object.keys(validationResult).length > 0) {
            setErrorMsg(validationResult);
            return;
        }

        const fetchUpdate = await updatePatient(patient);
        if(fetchUpdate.ok) {
            setPatientListUpdateDetector(!patientListUpdateDetector);
            handleClose();
        }
        if(fetchUpdate.status != 200) {
            const result = await fetchUpdate.json();
            setErrorMsg(result);
        }
    }

    const updatePatient = async (patient:Patient):Promise<Response> => {
        return await fetch("http://localhost:8081/patient/" + patient.id, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(patient)
        });
    }

    return (
        <>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Modal heading</Modal.Title>
                </Modal.Header>
                <Form onSubmit={updatePatientHandler}>
                    <Modal.Body>
                        <Form.Group>
                            <Form.Control type="text" name="id" value={patient.id} hidden readOnly></Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Family Name</Form.Label>
                            <Form.Control type="text" name="family" defaultValue={patient.family} required></Form.Control>
                            <Form.Text className="text-danger">
                                {errorMsg.family}
                            </Form.Text>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Given Name</Form.Label>
                            <Form.Control type="text" name="given" defaultValue={patient.given} required></Form.Control>
                            <Form.Text className="text-danger">
                                {errorMsg.given}
                            </Form.Text>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Date of Birth</Form.Label>
                            <Form.Control type="date" name="dob" defaultValue={patient.dob} required></Form.Control>
                            <Form.Text className="text-danger">
                                {errorMsg.dob}
                            </Form.Text>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Sex</Form.Label>
                            <Form.Check type="radio" name="sex" value="M" label="M" defaultChecked={patient.sex === "M"}></Form.Check>
                            <Form.Check type="radio" name="sex" value="F" label="F" defaultChecked={patient.sex === "F"}></Form.Check>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Address</Form.Label>
                            <Form.Control type="text" name="address" defaultValue={patient.address} required></Form.Control>
                            <Form.Text className="text-danger">
                                {errorMsg.address}
                            </Form.Text>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Phone</Form.Label>
                            <Form.Control type="text" name="phone" defaultValue={patient.phone} required></Form.Control>
                            <Form.Text className="text-danger">
                                {errorMsg.phone}
                            </Form.Text>
                        </Form.Group>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="primary" type="submit">
                            Update
                        </Button>
                        <Button variant="secondary" onClick={handleClose}>
                            Close
                        </Button>
                    </Modal.Footer>
                </Form>
            </Modal>
        </>
    )
}

export default PatientUpdate;