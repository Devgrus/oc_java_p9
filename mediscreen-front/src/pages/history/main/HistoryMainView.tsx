import React, {useEffect, useState} from 'react';
import {Link, useParams} from "react-router-dom";
import HistoryList from "../../../components/history/historyList/historyList.component";
import Header from "../../../components/base/header/header.component";

export type History = {
    id: string;
    patId: string;
    note: string;
    createdDate: string;
    lastModifiedDate: string;
}

const HistoryMainView = () => {
    const params = useParams();
    const [historyList, setHistoryList] = useState<History[]>([]);

    useEffect(() => {
        document.title = 'Mediscreen | History';
        getHistoryList();
    }, []);

    const getHistoryList = (): void => {
        console.log("Get history list");
        fetch("http://localhost:8082/patHistory?patId=" + params.id, {
            method: "GET",
        })
            .then(res => res.json())
            .then(res => setHistoryList(res))
            .catch(e => console.log(e));
    }

    const deleteButtonHandler = async (event: React.MouseEvent<HTMLButtonElement>, id:string): Promise<void> => {
        event.preventDefault();
        const deleteResult = await deleteHistory(id);
        if(deleteResult.ok) getHistoryList();
    }

    const deleteHistory = async (id: String):Promise<Response> => {
        return await fetch("http://localhost:8082/patHistory/" + id, {
            method: "DELETE",
        });
    }

    return (
        <>
            <Header />
            <Link className="btn btn-primary" to={`/history/add`} state={params}>+ Add a new history</Link>
            <div className="container">
                <HistoryList historyList={historyList} deleteButtonHandler={deleteButtonHandler} />
            </div>
        </>
    )
}

export default HistoryMainView;