'use strict';

class Counter extends React.Component {
    constructor(props) {
        super(props);
        this.state = {counter: 0};
    }

    click() {
        var current = this.state.counter;
        this.setState({counter: current + 1});
    }

    render() {
        return React.createElement('button',
            {onClick: () => this.click()},
            'Count: ' + this.state.counter
        );
    }
}

const domContainer = document.querySelector('#counter');
ReactDOM.render(React.createElement(Counter), domContainer);
