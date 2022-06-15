import React, {ChangeEvent, useEffect, useState} from 'react';

import Header from '../../../components/base/header/header.component';
import SearchBox from '../../../components/patient/searchBox/searchBox.component';
import PatientList from '../../../components/patient/patientList/patientList.component';
import PatientAdd from "../add/PatientAddView";

export type Patient = {
    id?: string;
    family: string;
    given: string;
    sex: string;
    dob: string;
    address: string;
    phone: string;
};

export type PatientError = {
    family?: string;
    given?: string;
    sex?: string;
    dob?: string;
    address?: string;
    phone?: string;
}

type patientProps = {
    searchField : string;
    setSearchField: (value: string)=>void;
}

const PatientMain = ({searchField, setSearchField} : patientProps) => {
    const [patientList, setPatientList] = useState<Patient[]>([]);
    const [patientListUpdateDetector, setPatientListUpdateDetector] = useState(false);
    const [show, setShow] = useState(false);

    const handleClose = ():void => setShow(false);
    const handleShow = (event:React.MouseEvent<HTMLButtonElement>, patient: Patient):void => {
        setShow(true);
    };

    useEffect(() => {
        document.title = 'Mediscreen | Search patients';
    }, []);

    useEffect(() => {
        console.log("patientList Update");
    }, [patientList]);

    useEffect(() => {
        getPatientList();
    }, [patientListUpdateDetector])

    const onChangeHandler = (event: ChangeEvent<HTMLInputElement>): void => {
        const searchFieldString = event.target.value;
        setSearchField(searchFieldString);
    };

    const deleteButtonHandler = async (event: React.MouseEvent<HTMLButtonElement>): Promise<void> => {
        event.preventDefault();
        const id = event.currentTarget.name;
        const deleteResult = await deletePatient(id);
        if(deleteResult.ok) getPatientList();
    }

    const getPatientList = (): void => {
        console.log("Get patient list");
        fetch("http://localhost:8081/patient?family=" + searchField, {
            method: "GET",
        })
            .then(res => res.json())
            .then(res => setPatientList(res))
            .catch(e => console.log(e));
    }

    const deletePatient = async (id: String):Promise<Response> => {
        return await fetch("http://localhost:8081/patient/" + id, {
            method: "DELETE",
        });
    }

    const onSubmitHandler = (event: React.FormEvent<HTMLFormElement>): void => {
        event.preventDefault();
        getPatientList();
    }

    const regexValidation = (patient:Patient): PatientError => {
        const errMsg:PatientError = {};
        if(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g.test(patient.family)) errMsg.family = "Should not use special characters";
        if(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g.test(patient.given)) errMsg.given = "Should not use special characters";
        if(/(^M$)|(^F$)/.test(patient.given)) errMsg.sex = "Only M or F";
        if(Date.now() < Date.parse(patient.dob)) errMsg.dob = "Date of birth could not be a future date";

        return errMsg;
    }



    return (
        <>
            <Header />
            <div className="container">
                <PatientAdd patientListUpdateDetector={patientListUpdateDetector}
                            setPatientListUpdateDetector={setPatientListUpdateDetector}
                            regexValidation={regexValidation}></PatientAdd>
                <SearchBox className="form" onChangeHandler={onChangeHandler}  onSubmitHandler={onSubmitHandler} />
                <PatientList patientListUpdateDetector={patientListUpdateDetector}
                             setPatientListUpdateDetector={setPatientListUpdateDetector}
                             patients={patientList} deleteButtonHandler={deleteButtonHandler}
                             regexValidation={regexValidation} />
            </div>
        </>
    );
};

export default PatientMain;