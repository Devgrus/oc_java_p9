import React from "react";
import Header from "../../components/base/header/header.component";
import {Link} from "react-router-dom";

const Page404 = () => {

    return (
        <>
            <Header />
            <h1>PAGE NOT FOUND</h1>
            <Link className="btn btn-primary" to={"/"}>Go to main page</Link>
        </>
    );
}

export default Page404;