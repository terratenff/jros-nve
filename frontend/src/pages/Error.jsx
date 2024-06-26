import { useRouteError } from "react-router-dom";

function Error() {
    const error = useRouteError();
    console.error(error);

    return (
        <div>
            <h1>Oops!</h1>
            <p>An error has occurred.</p>
            <i>{error.status + " - " + (error.statusText || error.message)}</i>
        </div>
    );
}

export default Error;
