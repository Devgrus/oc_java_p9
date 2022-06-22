import React, {useEffect, useState} from 'react';
import { Bar } from 'react-chartjs-2';
import {Card, ListGroup} from "react-bootstrap";
import {AssessmentData} from "../assessment/read/AssessmentRead.component";
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';
import {RiskLevel} from "../../pages/analyse/main/AnalyseMainView";

ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
);

type RiskLevelBySex = {
    male: RiskLevel;
    female: RiskLevel;
}

type ChartDiabetesAllProps = {

    assessmentList:AssessmentData[];
}

const ChartDiabetesSex = ({assessmentList} : ChartDiabetesAllProps) => {
    const [riskLevelBySex, setRiskLevelBySex] = useState({
        male: {None:0, BorderLine:0, InDanger:0, EarlyOnset:0},
        female: {None:0, BorderLine:0, InDanger:0, EarlyOnset:0}
    } as RiskLevelBySex);
    useEffect(() => {
        setRiskLevelBySex({
            male: {None:0, BorderLine:0, InDanger:0, EarlyOnset:0},
            female: {None:0, BorderLine:0, InDanger:0, EarlyOnset:0}
        });
        CountRiskLevel(assessmentList);
    }, [assessmentList])

    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top' as const,
            },
            title: {
                display: true,
                text: 'Chart.js Bar Chart',
            },
        },
        scales: {
            y: {
                ticks: {
                    stepSize: 1,
                }
            }
        }
    };

    const labels = ['None', 'Borderline', 'In Danger', 'Early onset'];



    const CountRiskLevel = (assessmentList: AssessmentData[]) => {
        const maleRisk:RiskLevel = {
            None: 0,
            BorderLine: 0,
            InDanger: 0,
            EarlyOnset: 0,
        }
        const femaleRisk:RiskLevel = {
            None: 0,
            BorderLine: 0,
            InDanger: 0,
            EarlyOnset: 0,
        }
        assessmentList.map(res => {
            switch (res.diabetesAssessment) {
                case 'None':
                    if(res.sex == "M") maleRisk.None++;
                    else femaleRisk.None++;
                    break;
                case 'BorderLine':
                    if(res.sex == "M") maleRisk.BorderLine++;
                    else femaleRisk.BorderLine++;
                    break;
                case 'InDanger':
                    if(res.sex == "M") maleRisk.InDanger++;
                    else femaleRisk.InDanger++;
                    break;
                case 'EarlyOnset':
                    if(res.sex == "M") maleRisk.EarlyOnset++;
                    else femaleRisk.EarlyOnset++;
                    break;
            }
        });
        setRiskLevelBySex({
            male: maleRisk,
            female: femaleRisk
        });
    }

    const data = {
        labels,
        datasets: [
            {
                label: 'F',
                data: [riskLevelBySex.female.None, riskLevelBySex.female.BorderLine, riskLevelBySex.female.InDanger, riskLevelBySex.female.EarlyOnset],
                backgroundColor: 'rgba(255, 99, 132, 0.5)',
            },
            {
                label: 'M',
                data: [riskLevelBySex.male.None, riskLevelBySex.male.BorderLine, riskLevelBySex.male.InDanger, riskLevelBySex.male.EarlyOnset],
                backgroundColor: 'rgba(53, 162, 235, 0.5)',
            },
        ],
    };

    return (
        <>
            <Card className="col-6">
                <ListGroup variant="flush">
                    <ListGroup.Item>Sex</ListGroup.Item>
                    <ListGroup.Item>
                        <Bar options={options} data={data} />
                    </ListGroup.Item>
                </ListGroup>
            </Card>
        </>
    );
}

export default ChartDiabetesSex;