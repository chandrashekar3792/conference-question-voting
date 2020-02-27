import React, { Component } from "react";
import {Header} from "../header/Header";
import {addQuestion} from "../../API/questions"
class AddQuestion extends Component {
  constructor(props) {
    super(props);
    this.state = {
        title:"",
        category:"",
        showError:false,
        errorMsg:"",
        successMsg:""
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleUserInput = this.handleUserInput.bind(this);
  }
  componentDidMount(){}
  handleUserInput(e) {
    console.log("this.state",this.state)
    const name = e.target.name;
    const value = e.target.value;
    this.setState({ [name]: value, showError:false });
  }
  handleSubmit(event) {
    event.preventDefault()
        if (this.state.title && this.state.category) {
            let {title,category}=this.state;
            addQuestion(title,category)
                .then(json => {
                    console.log("json",json);
                    this.setState({
                        successMsg: true,
                        title:"",
                        category:""
                    });
                })
                .catch(error => {
                    this.setState({
                        showError: true,
                        errorMsg: "Something Went Wrong"
                    });
                });
        } else {
            this.setState({
                showError: true,
                errorMsg:"Title and Category are mandatory"
            });
        }
    }
  render() {
    return(
      <div>
        <Header/>
        <div className="add-question">
          <form onSubmit={this.handleSubmit}>
          <h1>Add Question</h1>
            <label>Question Title</label>
            <input 
              type="text"
              id="title"
              value={this.state.title}
              name="title"
              placeholder="Your Question Statement"
              onChange={this.handleUserInput.bind(this)} />

            <label>Category</label>
            <input 
              type="text" 
              id="category" 
              value={this.state.category} 
              name="category" 
              placeholder="Your Question Category"
              onChange={this.handleUserInput.bind(this)} />

            <input type="submit" value="Submit"/>
            
            {this.state.successMsg &&(
              <div className="isa_info">
                Question Added Successfully.
               </div>
            )}
            {this.state.showError &&(
              <div className="isa_error">{this.state.errorMsg}</div>
            )}
          </form>
        </div>
      </div>
    )
  }
}

export default AddQuestion;
