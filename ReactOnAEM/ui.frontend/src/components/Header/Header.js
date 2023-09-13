import React from "react";
require('./Header.css');

const Header = (props) => {
    return (
        <>
        <div id="reactHeader">
            <h2>React on AEM {props.name}</h2>
        </div>
        </>
    );
}
export default Header;