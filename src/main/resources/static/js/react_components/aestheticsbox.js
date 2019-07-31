'use strict';

/**
 * This is a box that can be hidden, set to contain settings that can
 * be used to change the look of the site.
 */
class AestheticsBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {visible: false};

        this.boxTop = React.createElement(
            'div',
            {class: 'sideboxtop'},
            React.createElement(
                'p',
                {class: 'sideboxtop'},
                'Aesthetic Settings'
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
                'This website is currently a work in progress. So far, the only'
                + ' available setting to the user is the Dark Mode.'
            ),
            React.createElement(
                'checkbox',
                {id: 'darkmode0', name: 'Dark Mode0', value: 'cssmode0'},
                React.createElement(
                    'label',
                    {for: 'checkbox'},
                    ' Dark Mode0'
                )
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
    React.createElement(AestheticsBox),
    document.querySelector('#box_aesthetics')
);
