import React, {useState} from 'react';
import {Patient, PatientError} from "../../../pages/patient/main/PatientMainView";
import PatientUpdate from "../update/PatientUpdate.component";
import './patientList.component.css';
import {Link} from "react-router-dom";
import AssessmentRead from "../../assessment/read/AssessmentRead.component";

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
            <tr className="d-flex">
                <th scope="col" className="col-1">ID</th>
                <th scope="col" className="col-2">Name</th>
                <th scope="col" className="col-1">Sex</th>
                <th scope="col" className="col-1">DoB</th>
                <th scope="col" className="col-1">Address</th>
                <th scope="col" className="col-2">Phone</th>
                <th scope="col" className="col-1">History</th>
                <th scope="col" className="col-1">Assessment</th>
                <th scope="col" className="col-2">Operation</th>
            </tr>
            </thead>
            <tbody>
            {patients.map((patient) => {
                return (
                    <tr key={patient.id} className="d-flex">
                        <td className="col-1">{patient.id}</td>
                        <td className="text-truncate col-2">{patient.family} {patient.given}</td>
                        <td className="col-1">{patient.sex}</td>
                        <td className="col-1">{patient.dob}</td>
                        <td className="text-truncate col-1">{patient.address}</td>
                        <td className="text-truncate col-2">{patient.phone}</td>
                        <td className="col-1">
                            <Link className="btn btn-link p-0 text-black text-decoration-none" to={`/history/${patient.id}`}>Show</Link>
                        </td>
                        <td className="col-1">
                            <AssessmentRead id={patient.id} />
                        </td>
                        <td className="col-2">
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