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
        successMsg:"",
        options:{
          option1:"",
          option2:"",
          option3:'',
          option4:""
        }
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleUserInput = this.handleUserInput.bind(this);
  }
  componentDidMount(){}
  handleUserInput(e) {
    const name = e.target.name;
    const value = e.target.value;
    this.setState({ [name]: value, showError:false });
  }
  handleUserOptions(e){
    const name = e.target.name;
    const value = e.target.value;
    let newOptions=this.state.options;
    newOptions[name]=value;
    this.setState({ options: newOptions, showError:false });
  }
  handleSubmit(event) {
    event.preventDefault()
        if (this.state.title && this.state.category) {
            let {title,category,options}=this.state;
            addQuestion(title,category,options)
                .then(json => {
                    this.setState({
                        successMsg: true,
                        title:"",
                        category:"",
                        options:{
                          option1:"",
                          option2:"",
                          option3:'',
                          option4:""
                        }
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
             <div className="form-div">
                  <label className="input-label">Option 1</label>
                  <input style={{marginRight:20}}
                    type="text" 
                    id="option1"
                    name="option1"
                    placeholder="Enter Option 1"
                    value={this.state.options.option1}
                    onChange={this.handleUserOptions.bind(this)}
                    className="form-control"/>
                  <label className="input-label">Option 2</label>
                  <input
                    type="text" 
                    id="option2"
                    name="option2"
                    placeholder="Enter Option 2"
                    value={this.state.options.option2}
                    onChange={this.handleUserOptions.bind(this)}
                    className="form-control"/>
              </div>
              <div className="form-div">
                  <label  className="input-label">Option 3</label>
                  <input style={{marginRight:20}}
                    type="text" 
                    id="option3"
                    name="option3"
                    placeholder="Enter Option 3"
                    value={this.state.options.option3}
                    onChange={this.handleUserOptions.bind(this)}
                    className="form-control"/>
                
                  <label  className="input-label">Option 4</label>
                  <input
                    type="text" 
                    id="option4"
                    name="option4"
                    placeholder="Enter Option 4"
                    value={this.state.options.option4}
                    onChange={this.handleUserOptions.bind(this)}
                    className="form-control"/>
              </div>
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
