import React, {useEffect, useState} from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Pie } from 'react-chartjs-2';
import {Card, ListGroup} from "react-bootstrap";
import {AssessmentData} from "../assessment/read/AssessmentRead.component";

ChartJS.register(ArcElement, Tooltip, Legend);

type RiskLevel = {
    None: number;
    BorderLine: number;
    InDanger: number;
    EarlyOnset: number;
}

type ChartDiabetesAllProps = {

    assessmentList:AssessmentData[];
}

const ChartDiabetesAll = ({assessmentList} : ChartDiabetesAllProps) => {
    const [riskLevel, setRiskLevel] = useState({None:0, BorderLine:0, InDanger:0, EarlyOnset:0} as RiskLevel);
    useEffect(() => {
        setRiskLevel({None:0, BorderLine:0, InDanger:0, EarlyOnset:0});
        CountRiskLevel(assessmentList);
    }, [assessmentList])
    const CountRiskLevel = (assessmentList: AssessmentData[]) => {
        let none:number = 0;
        let borderline:number = 0;
        let inDanger:number = 0;
        let earlyOnset:number = 0;
        assessmentList.map(res => {
            switch (res.diabetesAssessment) {
                case 'None':
                    none++;
                    break;
                case 'BorderLine':
                    borderline++;
                    break;
                case 'InDanger':
                    inDanger++;
                    break;
                case 'EarlyOnset':
                    earlyOnset++;
                    break;
            }
        });
        setRiskLevel({
            None: none,
            BorderLine: borderline,
            InDanger: inDanger,
            EarlyOnset: earlyOnset,
        })
    }

    const data = {
        labels: ['None', 'Borderline', 'In Danger', 'Early Onset'],
        datasets: [
            {
                label: '# of Votes',
                data: [riskLevel.None, riskLevel.BorderLine, riskLevel.InDanger, riskLevel.EarlyOnset],
                backgroundColor: [
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(153, 102, 255, 0.2)',

                ],
                borderColor: [
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(255, 99, 132, 1)',
                    'rgba(153, 102, 255, 1)',

                ],
                borderWidth: 1,
            },
        ],
    };

    return (
        <>
            <Card className="col-6">
                <ListGroup variant="flush">
                    <ListGroup.Item>Total</ListGroup.Item>
                    <ListGroup.Item>
                        <Pie data={data} height={300} options={{ maintainAspectRatio: false }} />
                    </ListGroup.Item>
                </ListGroup>
            </Card>
        </>
    );
}

export default ChartDiabetesAll;