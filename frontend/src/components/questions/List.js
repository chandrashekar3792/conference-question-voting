

import React, { Component } from "react";
import {Header} from "../header/Header";
import {getQuestions} from "../../API/questions"
import "./Style.css";

let EachQuestion=(request)=>{
  return request.data.map((question, i)=>{
      return(<div className="flex flex3">
        <div className="child flex3-child">{question.id}</div>
        <div className="child flex3-child flex-child-large">{question.title}</div>
        <div className="child flex3-child flex-child-medium">{question.category}</div>
        <div className="child flex3-child">{question.votes}</div>
      </div>)
  })
}


class ListQuestions extends Component {
  constructor() {
    super();
    this.state = {
      questions:[]
    };
  }
  componentDidMount(){
    getQuestions().then((result)=>{
      console.log("result",result.data);
      this.setState({
        questions:result.data,
      })
    })
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
            <div className="child flex2-child">Votes</div>
          </div>
          <EachQuestion data={this.state.questions}/>
        </div>    
      </div>
    )
  }
}

export default ListQuestions;
