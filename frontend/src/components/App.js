import React, { Component } from "react";
import { BrowserRouter, Route,Redirect } from "react-router-dom";
import ListQuestions from "./questions/List";
import AddQuestion from "./questions/Add";
import VoteQuestion from "./questions/Vote";

class App extends Component {
  constructor(props){
    super(props)
    this.state={
      // isAuthenticated:false
    }
  }
  componentDidMount() {
   
  }
  render() {

    return (
      <div className="container">
        <BrowserRouter>
          <div>
            <Route exact path='/questions' component={ListQuestions} />
            <Route exact path='/questions/add' component={AddQuestion} />
            <Route exact path='/questions/vote' component={VoteQuestion} />
          </div>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
