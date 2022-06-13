import React, {useState} from 'react';
import {Patient, PatientError} from "../../../pages/patient/main/PatientMainView";
import PatientUpdate from "../../../pages/patient/update/PatientUpdateView";
import {Link} from "react-router-dom";

type PatientListProps = {
    patients: Patient[];
    deleteButtonHandler: (event: React.MouseEvent<HTMLButtonElement>)=>void;
    patientListUpdateDetector: boolean;
    setPatientListUpdateDetector: (value: boolean)=>void;
    regexValidation: (patient: Patient)=>PatientError;
};

const PatientList = ({ patients, deleteButtonHandler, patientListUpdateDetector, setPatientListUpdateDetector, regexValidation }: PatientListProps) => {
    const [show, setShow] = useState(false);
    const [patient, setPatient] = useState<Patient>({} as Patient);
    const [errorMsg, setErrorMsg] = useState<PatientError>({} as PatientError);

    const handleClose = () => {
        setShow(false);
        setErrorMsg({});
    };
    const handleShow = (event:React.MouseEvent<HTMLButtonElement>, patient: Patient):void => {
        setShow(true);
        setPatient(patient);
    };



    return (
        <table className='patient-list table'>
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Family</th>
                <th scope="col">Given</th>
                <th scope="col">Sex</th>
                <th scope="col">DoB</th>
                <th scope="col">Address</th>
                <th scope="col">Phone</th>
                <th scope="col">History</th>
                <th scope="col">Operation</th>
            </tr>
            </thead>
            <tbody>
            {patients.map((patient) => {
                return (
                    <tr key={patient.id}>
                        <td>{patient.id}</td>
                        <td>{patient.family}</td>
                        <td>{patient.given}</td>
                        <td>{patient.sex}</td>
                        <td>{patient.dob}</td>
                        <td>{patient.address}</td>
                        <td>{patient.phone}</td>
                        <td>
                            <Link className="btn btn-link p-0 text-black text-decoration-none" to={`/history/${patient.id}`}>Detail</Link>
                        </td>
                        <td>
                            <button className="btn btn-link p-0 text-black text-decoration-none" onClick={(e) => handleShow(e, patient)}>EDIT |&nbsp;</button>
                            <button type="button"
                                    className="btn btn-link p-0 text-danger text-decoration-none"
                                    name={patient.id}
                                    onClick={deleteButtonHandler}>DELETE</button>
                        </td>
                    </tr>
                )
            })}
            </tbody>
            <PatientUpdate show={show}
                           handleClose={handleClose}
                           patient={patient}
                           patientListUpdateDetector={patientListUpdateDetector}
                           setPatientListUpdateDetector={setPatientListUpdateDetector}
                           regexValidation={regexValidation}
                           errorMsg={errorMsg}
                           setErrorMsg={setErrorMsg} />
        </table>

    )
};

export default PatientList;