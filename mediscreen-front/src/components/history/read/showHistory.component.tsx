import React from 'react';
import {History} from "../../../pages/history/main/HistoryMainView";

type ShowHistoryProps = {
    history: History;
}

const ShowHistory = ({history} : ShowHistoryProps) => {

    return (
        <>
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">Patient ID</th>
                        <th scope="col">Created Date</th>
                        <th scope="col">Last Modified Date</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="col">{history.patId}</th>
                        <th scope="col">{history.createdDate}</th>
                        <th scope="col">{history.lastModifiedDate}</th>
                    </tr>
                </tbody>
            </table>
            <h1 className="h3">NOTE</h1>
            <textarea className="form-control bg-white" readOnly value={history.note}></textarea>
        </>
    )
}

export default ShowHistory;