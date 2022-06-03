import React from 'react';
import {useRoutes} from 'react-router-dom';
import './App.css';

import Home from './pages/home/Home';
import PatientMainView from './pages/patient/main/PatientMainView';

const App = () => {
    const mainRoutes = {
        path: '/',
        element: <Home />,
    };

    const patientRoutes = {
        path: 'patient',
        element: <PatientMainView />,
        children: [
            // {path: ':id', element: <Patient />},
            // {path: 'add', element: <PatientAddView />},
            // {path: 'update', element: <PatientUpdateView />},
        ],
    };

    const routing = useRoutes([mainRoutes, patientRoutes]);
    return (
    <div className="App">
        {routing}
    </div>
  );
}

export default App;
