import "../styles/generalStructure.css";
import "../styles/generalLight.css";
import "../styles/generalDark.css";
import { toggleDarkMode } from "../utilities/ThemeHandler";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import Nav from "react-bootstrap/Nav";
import { Link } from "react-router-dom";

function TopBar() {

    return (
        <Navbar className="top-bar">
            <Container>
                <Navbar.Brand as={Link} to="/">jros-nve</Navbar.Brand>
                <Navbar.Toggle />
                <Navbar.Collapse>
                    <Nav>
                        <Nav.Link as={Link} to="/">Home</Nav.Link>
                        <Nav.Link as={Link} to="login">Login</Nav.Link>
                        <NavDropdown title="Story" className="top-bar">
                            <NavDropdown.Item as={Link} to="new-story" className="top-bar">New Story</NavDropdown.Item>
                            <NavDropdown.Item as={Link} to="fill-prompts" className="top-bar">Fill Prompts</NavDropdown.Item>
                            <NavDropdown.Item as={Link} to="read-story" className="top-bar">Read Story</NavDropdown.Item>
                        </NavDropdown>
                        <Nav.Link as={Link} to="about">About</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
                <Button onClick={() => toggleDarkMode()}>Text</Button>
                <Form className="d-flex">
                    <Form.Control
                    type="search"
                    placeholder="Search"
                    className="me-2"
                    aria-label="Search"
                    />
                    <Button variant="outline-success">Search</Button>
                </Form>
            </Container>
        </Navbar>
    );
}

export default TopBar;
