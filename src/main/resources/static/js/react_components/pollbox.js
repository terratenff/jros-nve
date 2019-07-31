'use strict';

/**
 * This is a box that can be hidden, set to contain a poll that a user can
 * vote on.
 */
class PollBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {visible: false};

        this.boxTop = React.createElement(
            'div',
            {class: 'sideboxtop'},
            React.createElement(
                'p',
                {class: 'sideboxtop'},
                'Storytellers Poll of the Month'
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
                'This website is currently a work in progress. How is the work coming along?'
            ),
            React.createElement(
                'a',
                {href: '/'},
                'Vote (TODO)'
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
    React.createElement(PollBox),
    document.querySelector('#box_poll')
);
