import { useRouteError, isRouteErrorResponse } from "react-router-dom";

function Error() {
    const error = useRouteError();

    let errorMessage: string;
    let errorStatus: string;

    if (isRouteErrorResponse(error)) {
        errorMessage = error.statusText;
        errorStatus = String(error.status);
    } else if (typeof error === 'string') {
        errorMessage = error;
        errorStatus = "4xx";
    } else {
        errorMessage = 'Unknown error';
        errorStatus = "4xx";
  }

    return (
        <div>
            <h1>Oops!</h1>
            <p>An error has occurred.</p>
            <i>{errorStatus + " - " + (errorMessage)}</i>
        </div>
    );
}

export default Error;
