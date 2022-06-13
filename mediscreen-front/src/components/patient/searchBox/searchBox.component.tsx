import React, {ChangeEvent, FormEventHandler} from 'react';

type SearchBoxProps = {
    className: string;
    onChangeHandler: (event: ChangeEvent<HTMLInputElement>)=>void;
    onSubmitHandler: (event: React.FormEvent<HTMLFormElement>)=>void;
};

const SearchBox = ({className, onChangeHandler, onSubmitHandler}:SearchBoxProps) => {


    return (
        <form className={`form ${className}`} onSubmit={onSubmitHandler}>
            <input className="mx-2" type="search" name="family" placeholder="Nom de Famille" onChange={onChangeHandler} autoFocus />
            <button type={"submit"} className={"btn btn-success"}>Search!</button>
        </form>
    )
}

export default SearchBox;