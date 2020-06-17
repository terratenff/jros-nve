'use strict';

/**
 * This is a box that can be hidden, set to contain the means of creating
 * a new account.
 */
class SignupBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {visible: false};

        this.boxTop = React.createElement(
            'div',
            {class: 'sideboxtop'},
            React.createElement(
                'p',
                {class: 'sideboxtop'},
                'Sign Up'
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
                'Create a new account here!'
            ),
            React.createElement(
                'form',
                {action: '/signup', method: 'post'},
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
                React.createElement('label', {for: 'pwd_repeat'}, 'Repeat'),
                React.createElement(
                    'input',
                    {type: 'password', id: 'pwd_repeat', name: 'pwd_repeat'}
                ),
                React.createElement('input', {type: 'submit', value: 'Create New Account'})
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
    React.createElement(SignupBox),
    document.querySelector('#box_signup')
);
