import React, {ChangeEvent, useEffect, useState} from 'react';
import Header from "../../../components/base/header/header.component";
import SearchBox from "../../../components/patient/searchBox/searchBox.component";
import {AssessmentData} from "../../../components/assessment/read/AssessmentRead.component";
import ChartDiabetesAll from "../../../components/analyse/chart-diabetes-all.component";
import {CardGroup} from "react-bootstrap";
import ChartDiabetesSex from "../../../components/analyse/chart-diabetes-sex.component";

export type RiskLevel = {
    None: number;
    BorderLine: number;
    InDanger: number;
    EarlyOnset: number;
}

const AnalyseMain = () => {
    const [searchField, setSearchField] = useState('');
    const [assessmentList, setAssessmentList] = useState<AssessmentData[]>([]);
    const [errMsg, setErrMsg] = useState('');

    const onChangeHandler = (event: ChangeEvent<HTMLInputElement>): void => {
        const searchFieldString = event.target.value;
        setSearchField(searchFieldString);
    };

    const onSubmitHandler = async (event: React.FormEvent<HTMLFormElement>): Promise<void> => {
        event.preventDefault();
        const getResult = await getAssessmentList();
        const res = await getResult.json();
        if(getResult.ok && res.length != 0) {
            setAssessmentList(res);
            setErrMsg('');
        } else {
            setAssessmentList([]);
            setErrMsg("Family name not found");
        }
    }

    const getAssessmentList = async ():Promise<Response> => {
        return await fetch("http://localhost:8083/assess/family/" + searchField, {
            method: "GET",
        });
    }

    return (
        <>
            <Header />
            <div className="container">
                <h1 className="h4 mb-3">Analyse the diabetes assessments with family name</h1>
                <SearchBox className="form mb-2" onChangeHandler={onChangeHandler}  onSubmitHandler={onSubmitHandler} />
                <div className="text-danger">{errMsg}</div>
                <CardGroup className="d-flex mt-3">
                    <ChartDiabetesAll assessmentList={assessmentList} />
                    <ChartDiabetesSex assessmentList={assessmentList} />
                </CardGroup>
            </div>
        </>
    );
}

export default AnalyseMain;