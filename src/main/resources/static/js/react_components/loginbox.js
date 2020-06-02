'use strict';

/**
 * This is a box that can be hidden, set to contain a set of links that
 * could be used as shortcuts around the website.
 */
class LoginBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {visible: false};

        this.boxTop = React.createElement(
            'div',
            {class: 'sideboxtop'},
            React.createElement(
                'p',
                {class: 'sideboxtop'},
                'Login'
            ),
            React.createElement(
                'button',
                {onClick: () => this.click(), class: 'sideboxtop'},
                '...'
            )
        );

        this.boxContent = React.createElement(
            'div',
            {class: 'sideboxcontent'},
            React.createElement(
                'p',
                {class: 'sideboxcontent'},
                'Posting stories is possible without logging in, but extra benefits come with an account.'
            ),
            React.createElement(
                'form',
                {action: '/login', method: 'post'},
                React.createElement('label', {for: 'username'}, 'Username'),
                React.createElement(
                    'input',
                    {type: 'text', id: 'username', name: 'username'}
                ),
                React.createElement('label', {for: 'pwd'}, 'Password'),
                React.createElement(
                    'input',
                    {type: 'password', id: 'pwd', name: 'pwd'}
                ),
                React.createElement('input', {type: 'submit', value: 'Log In'})
            ),
            React.createElement(
                'button',
                {onClick: () => window.location.href = '/api'},
                'New Account'
            )
        );
    }

    click() {
        if (this.state.visible === true) {
            this.setState({visible: false});
        } else {
            this.setState({visible: true});
        }
    }

    render() {
        if (this.state.visible === true) {
            return React.createElement(
                'div',
                {class: 'sideboxparent'},
                this.boxTop,
                this.boxContent
            );
        } else {
            return this.boxTop;
        }
    }
}

ReactDOM.render(
    React.createElement(LoginBox),
    document.querySelector('#box_login')
);
