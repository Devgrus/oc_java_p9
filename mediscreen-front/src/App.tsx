import React, {useState} from 'react';
import {useRoutes} from 'react-router-dom';
import './App.css';

import Home from './pages/home/Home';
import PatientMainView from './pages/patient/main/PatientMainView';
import HistoryMainView from "./pages/history/main/HistoryMainView";
import HistoryReadView from "./pages/history/read/HistoryReadView";
import HistoryEditView from "./pages/history/edit/HistoryEditView";
import HistoryAddView from "./pages/history/add/HistoryAddView";
import AnalyseMainView from "./pages/analyse/main/AnalyseMainView";
import Page404 from "./pages/error/404";

const App = () => {
    const [searchField, setSearchField] = useState('');

    const mainRoutes = {
        path: '/',
        element: <Home />,
    };

    const patientRoutes = {
        path: 'patient',
        element: <PatientMainView searchField={searchField} setSearchField={setSearchField} />,
        children: [
            // {path: ':id', element: <Patient />},
            // {path: 'add', element: <PatientAddView />},
            // {path: 'update', element: <PatientUpdateView />},
        ],
    };

    const historyRoutes = {
        path: 'history',
        // element:
        children: [
            {path: ':id', element: <HistoryMainView />},
            {path: 'add', element: <HistoryAddView />},
            {path: 'read/:id', element: <HistoryReadView />},
            {path: 'edit/:id', element: <HistoryEditView />},
        ],
    };

    const NotFoundPage = {
        path: '*',
        element: <Page404 />
    }

    const analyseRoutes = {
        path: 'analyse',
        element: <AnalyseMainView />
    };

    const routing = useRoutes([mainRoutes, patientRoutes, historyRoutes, analyseRoutes, NotFoundPage]);
    return (
    <div className="App">
        {routing}
    </div>
  );
}

export default App;
