import React, {useEffect, useState} from 'react';
import Header from "../../../components/base/header/header.component";
import ShowHistory from "../../../components/history/read/showHistory.component";
import {History} from "../main/HistoryMainView";
import {useParams} from "react-router-dom";

const HistoryReadView = () => {
    const params = useParams();
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


    return (
        <>
            <Header />
            <div className="container">
                <ShowHistory history={history} />
            </div>
        </>
    );
}

export default HistoryReadView;