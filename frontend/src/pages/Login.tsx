import "bootstrap/dist/css/bootstrap.min.css";

import Button from "react-bootstrap/Button";

function Login() {
    return (
    <div>
        <Button onClick={() => console.log("test")}>
            Button 1
        </Button>
        <Button onClick={() => console.log("test")}>
            Button 2
        </Button>
    </div>
    );
}

export default Login;
