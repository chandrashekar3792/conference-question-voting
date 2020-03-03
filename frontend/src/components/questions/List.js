

import React, { Component } from "react";
import {Header} from "../header/Header";
import {getQuestions,getChoices} from "../../API/questions"
import "./Style.css";

let EachOptions=(request)=>{
  let choices=request.choices.sort((a, b) => (a.votes > b.votes) ? 1 : -1).reverse()
  return choices.map((choice, i)=>{
    return(<div className="choices-list card">
      <div>{choice.statement}:</div>
      <div><b>{choice.votes}</b></div>
    </div>)
})
}
let EachQuestion=(request)=>{
  return request.data.map((question, i)=>{
      return(<div className="flex flex3">
        <div className="child flex3-child">{i+1}</div>
        <div className="child flex3-child flex-child-large">{question.title}</div>
        <div className="child flex3-child flex-child-medium">{question.category}</div>
        <div className="child flex3-child flex-child-large choices-div">
          <EachOptions choices={question.choices}/>
        </div>
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
      getChoices().then((choices)=>{
        let questionsData=result.data;
        for(let i=0;i<questionsData.length;i++){
          console.log("uestionsData[i].choices",questionsData[i].choices)
            for(let j=0;j<questionsData[i].choices.length;j++){
              console.log("j")
                let index=choices.data.findIndex((item)=>{
                  console.log("item",item)
                  return item.choiceId===questionsData[i].choices[j].id});
                if(index>=0){
                    questionsData[i].choices[j].votes=choices.data[index].voteCount;
                }else{
                  questionsData[i].choices[j].votes=0;
                }
            }
          }
        this.setState({
          questions:questionsData,
        })
            
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
            <div className="child flex2-child flex-child-large">Votes</div>
          </div>
          <EachQuestion data={this.state.questions}/>
        </div>    
      </div>
    )
  }
}

export default ListQuestions;
