import React, { useEffect } from 'react';

import Header from '../../components/base/header/header.component';

const Home = () => {
    useEffect(() => {
        document.title = 'Mediscreen | Home';
    }, []);
    return (
        <>
            <Header />
            <div className="container">
                <h1>Bienvenue chez Mediscreen !</h1>
            </div>
        </>
    );
};

export default Home;