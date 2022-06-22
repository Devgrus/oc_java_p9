import React from 'react';
import { Link } from 'react-router-dom';
import logo from "./../../../assets/logo/logo-mediscreen.png";

const Header = () => {
    return (
        <header className="d-flex flex-wrap justify-content-center px-2 py-3 mb-4 border-bottom">
            <Link className="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none" to="/">
                <img className="img-fluid" src={logo} alt={'logo-mediscreen'} width={30} height={30} />
                <div className="h2 mx-2 my-0">Mediscreen</div>
            </Link>
            <ul className="nav nav-pills">
                <li className="nav-item">
                    <Link className="nav-link" to="/">Home</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/patient">patient</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/analyse">analyse</Link>
                </li>
            </ul>
        </header>
    )
}

export default Header