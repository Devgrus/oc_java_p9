import {Button, Form, Modal} from "react-bootstrap";
import React, {useState} from "react";
import {Patient, PatientError} from "../../../pages/patient/main/PatientMainView";

type addProps = {
    patientListUpdateDetector: boolean;
    setPatientListUpdateDetector: (value: boolean)=>void;
    regexValidation: (patient: Patient) => PatientError;
}

const PatientAdd = ({patientListUpdateDetector, setPatientListUpdateDetector, regexValidation}: addProps) => {
    const [show, setShow] = useState(false);
    const [errorMsg, setErrorMsg] = useState<PatientError>({} as PatientError);

    const handleClose = () => {
        setShow(false);
        setErrorMsg({});
    };
    const handleShow = () => setShow(true);
    const savePatient = async (patient:Patient):Promise<Response> => {
        return await fetch("http://localhost:8081/patient/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(patient)
        });
    }

    const addPatientHandler = async (event: React.FormEvent<HTMLFormElement>): Promise<void> => {
        event.preventDefault();
        const patient:Patient = {
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

        const fetchSave = await savePatient(patient);
        if(fetchSave.ok) {
            setPatientListUpdateDetector(!patientListUpdateDetector);
            handleClose();
        }
        if(fetchSave.status != 201) {
            const result = await fetchSave.json();
            setErrorMsg(result);
        }
    }

    return (
        <>
            <Button className="mb-3" variant="primary" onClick={handleShow}>
                + Add Patient
            </Button>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Patient</Modal.Title>
                </Modal.Header>
                <Form onSubmit={addPatientHandler}>
                    <Modal.Body>
                        <Form.Group>
                            <Form.Label>Family Name</Form.Label>
                            <Form.Control type="text" name="family" required></Form.Control>
                            <Form.Text className="text-danger">
                                {errorMsg.family}
                            </Form.Text>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Given Name</Form.Label>
                            <Form.Control type="text" name="given" required></Form.Control>
                            <Form.Text className="text-danger">
                                {errorMsg.given}
                            </Form.Text>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Date of Birth</Form.Label>
                            <Form.Control type="date" name="dob" required></Form.Control>
                            <Form.Text className="text-danger">
                                {errorMsg.dob}
                            </Form.Text>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Sex</Form.Label>
                            <Form.Check type="radio" name="sex" value="M" label="M"></Form.Check>
                            <Form.Check type="radio" name="sex" value="F" label="F"></Form.Check>
                            <Form.Text className="text-danger">
                                {errorMsg.sex}
                            </Form.Text>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Address</Form.Label>
                            <Form.Control type="text" name="address" required></Form.Control>
                            <Form.Text className="text-danger">
                                {errorMsg.address}
                            </Form.Text>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Phone</Form.Label>
                            <Form.Control type="text" name="phone" required></Form.Control>
                            <Form.Text className="text-danger">
                                {errorMsg.phone}
                            </Form.Text>
                        </Form.Group>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="primary" type="submit">
                            Save
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

export default PatientAdd;