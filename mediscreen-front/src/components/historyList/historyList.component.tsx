import React from 'react';
import {History} from "../../pages/history/main/HistoryMainView";
import {Link} from "react-router-dom";

type historyListProps = {
    historyList: History[];
    deleteButtonHandler: (event: React.MouseEvent<HTMLButtonElement>, historyId:string)=>void;
}

const HistoryList = ({historyList, deleteButtonHandler} : historyListProps) => {

    return (
        <table className='patient-list table'>
            <thead>
            <tr>
                <th>No.</th>
                <th scope="col">Created Date</th>
                <th scope="col">Last Modified Date</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            {historyList.map((history:History, index:number) => {
                return (
                    <tr key={history.id}>
                        <td>{index + 1}</td>
                        <td>{history.createdDate.toString()}</td>
                        <td>{history.lastModifiedDate.toString()}</td>
                        <td>
                            <Link className="btn btn-link p-0 text-black text-decoration-none" to={`/history/read/${history.id}`}>READ |&nbsp;</Link>
                            <Link className="btn btn-link p-0 text-black text-decoration-none" to={`/history/edit/${history.id}`}>EDIT |&nbsp;</Link>
                            <button type="button" className="btn btn-link p-0 text-danger text-decoration-none"
                                    onClick={e => deleteButtonHandler(e, history.id)}>DELETE</button>
                        </td>
                    </tr>
                )
            })}
            </tbody>
        </table>

    )
}

export default HistoryList