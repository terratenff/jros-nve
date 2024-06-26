import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Home from './pages/Home';
import Login from './pages/Login';
import Error from './pages/Error';
import Root from './pages/Root';

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        errorElement: <Error />,
        children: [
            {
                path: "/",
                element: <Home />
            },
            {
                path: "/new-story",
                element: <p>New Story</p>
            },
            {
                path: "/fill-prompts",
                element: <p>Fill Prompts</p>
            },
            {
                path: "/read-story",
                element: <p>Read Story</p>
            },
            {
                path: "/about",
                element: <p>About</p>
            },
            {
                path: "/login",
                element: <Login />
            }
        ]
    }
]);

const root = ReactDOM.createRoot(document.getElementById('root')!);
root.render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);
