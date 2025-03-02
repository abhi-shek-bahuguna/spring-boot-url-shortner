import React, { useState } from "react";

const UrlForm = () => {
    const [text,setText] = useState("");
    const [responseVisibility,setResponseVisibility] = useState("hidden");
    const [responseText,setResponseText] = useState("");
    const [originalUrl,setOriginalUrl] = useState("");

    const onChange = (e) =>{
        setText(e.target.value);
    }

    const onSubmit = async(e) =>{
        e.preventDefault();
        let response = await fetch("http://localhost:8080/post",
            {
                method:"POST",
                headers:{
                    "Content-Type":"text/plain"
                },
                body:text
            }
        );

        let data = await response.text();
        setResponseText(data);

        response = await fetch(`http://localhost:8080/get/${data}`,
            {
                method:"GET",
                headers:{
                    "Content-Type" : "text/plain"
                }
            }
        );

        data = await response.text();
        setOriginalUrl(data)
        setResponseVisibility("visible");
    }

    const formStyle = {
        width: "400px",
        padding: "20px",
        borderRadius: "10px",
        boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.2)",
        backgroundColor: "#fff",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center"
    };

    const labelStyle = {
        marginRight: "20px",
        // padding: "20px"
    };

    const buttonSytle = {
        marginTop: "20px",
        marginBottom: "20px",
        padding: "9px 9px",
        fontSize: "16px",
        backgroundColor: "grey",
        color: "white",
        border: "none",
        borderRadius: "8px",
        cursor: "pointer",
        transition: "all 0.3s ease-in-out",
    };

    const inputStyle = {
        outline: "none",
        border: "none",
        borderBottom: "1px solid rgb(24, 26, 27)"
    }

    return (
        <form onSubmit={onSubmit} style={formStyle}>
        <div>
        <label style={labelStyle} >Enter your URL</label>
        <input onChange={onChange}
            value={text}
            style={inputStyle}
        />
        </div>
        <button style={buttonSytle}>Submit</button>
        <div style={{visibility: responseVisibility}}>
            <label>Your short URL : </label>
            <a href={originalUrl} target="_blank">
                {responseText}
            </a>
        </div>
        </form>
    );
};

export default UrlForm;