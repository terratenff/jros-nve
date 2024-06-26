import { Outlet } from "react-router-dom";
import TopBar from "../components/TopBar";
import Footer from "../components/Footer";

function Root() {
    return (
        <>
            <TopBar />
            <div id="root-contents">
                <p>
                    This is the root page.
                </p>
                <Outlet />
            </div>
            <Footer />
        </>
    );
}

export default Root;
