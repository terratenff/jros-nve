'use strict';

/**
 * This is a box that can be hidden, set to contain a set of links to stories
 * that are currently highlighted.
 */
class HighlightsBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {visible: false};

        this.boxTop = React.createElement(
            'div',
            {class: 'sideboxtop'},
            React.createElement(
                'p',
                {class: 'sideboxtop'},
                'Storytellers Highlights'
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
                'This website is currently a work in progress. Here one can '
                + 'select a unique story, based on specific statistics.'
            ),
            React.createElement(
                'a',
                {href: '/'},
                'Most Viewed Story (TODO)'
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
    React.createElement(HighlightsBox),
    document.querySelector('#box_highlights')
);
