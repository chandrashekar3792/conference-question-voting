
import React, { Component } from "react";
import {Header} from "../header/Header";
import {getQuestions,voteQuestion} from "../../API/questions"
import "./Style.css";

class VoteQuestion extends Component {
  constructor() {
    super();
    this.state = {
      questions:[]
    };
    this.submitVote=this.submitVote.bind(this);
  }
  componentDidMount(){
    getQuestions().then((result)=>{
      // console.log("result",result.data);
      this.setState({
        questions:result.data,
      })
    })
  }

  submitVote=(question,i)=>{
    console.log("question",question);
    question.voted=true
    let questionsNew=this.state.questions;
    questionsNew[i]=question;
    this.setState({
      questions:questionsNew
    });
    voteQuestion(question.id)
    .then(()=>{})
    // e.preventDefault();
  }
  render() {
    return(
      <div>
        <Header auth={this.state.isAuthenticated}/>
        <div className="list-questions">
          <div className="flex flex2">
            <div className="child flex2-child">#</div>
            <div className="child flex2-child flex-child-large">Title</div>
            <div className="child flex2-child flex-child-medium">Category</div>
            <div className="child flex2-child">Vote?</div>
          </div>
          {this.state.questions.map((question,i)=>{
          return(<div className="flex flex3" key={question.id}>
                  <div className="child flex3-child">{i+1}</div>
                  <div className="child flex3-child flex-child-large">{question.title}</div>
                  <div className="child flex3-child flex-child-medium">{question.category}</div>
                  <div className="pad-zero child flex3-child">
                  <button type="button" disabled={question.voted} id={question.id} name={question.id,i} className="button-yes" onClick={()=>this.submitVote(question)} type="button" >Yes</button>
                  </div>
                </div>)
          })}
        </div>    
      </div>
    )
  }
}

export default VoteQuestion;
