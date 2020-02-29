
import React, { Component } from "react";
import {Header} from "../header/Header";
import {getQuestions,voteQuestion} from "../../API/questions"
import "./Style.css";

class VoteQuestion extends Component {
  constructor() {
    super();
    this.state = {
      questions:[],
      choice:""
    };
    this.submitVote=this.submitVote.bind(this);
    this.handleUserInput=this.handleUserInput.bind(this);
  }
  componentDidMount(){
    getQuestions().then((result)=>{
      // console.log("result",result.data);
      this.setState({
        questions:result.data,
      })
    })
  }
  handleUserInput(e) {
    const name = e.target.name;
    const value = e.target.value;
    console.log("name",name,value)
    this.setState({ [name]: value, showError:false });
  }
  submitVote=(question,i)=>{
    console.log("question",question);
    question.voted=true
    let questionsNew=this.state.questions;
    questionsNew[i]=question;
    this.setState({
      questions:questionsNew
    });
    voteQuestion(question.id,this.state.choice)
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
            <div className="child flex2-child flex-child-large">Vote?</div>
          </div>
          {this.state.questions.map((question,i)=>{
          return(<div className="flex flex3" key={question.id}>
                  <div className="child flex3-child">{i+1}</div>
                  <div className="child flex3-child flex-child-large">{question.title}</div>
                  <div className="child flex3-child flex-child-medium">{question.category}</div>
                  <div className="pad-zero child flex3-child flex-child-large select-div">
                    <div class="select">
                      <select name="choice" id="choice" onChange={this.handleUserInput.bind(this)} value={this.state.choice}>
                      <option selected disabled>Choose an option</option>
                      {question.choices.map((choice,i)=>{
                          return (
                            <option id={choice.id} name={choice.id} value={choice.id}>{choice.statement}</option>)
                      })}
                      </select>
                    </div>
                  <button type="button" disabled={question.voted} id={question.id} name={question.id,i} className="button-yes" onClick={()=>this.submitVote(question)} type="button" >Vote</button>
                  </div>
                </div>)
          })}
        </div>    
      </div>
    )
  }
}

export default VoteQuestion;
