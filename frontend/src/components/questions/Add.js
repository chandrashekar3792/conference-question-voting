import React, { Component } from "react";
import {Header} from "../header/Header";
class AddQuestion extends Component {
  constructor() {
    super();
    this.state = {
    };
  }
  componentDidMount(){
    // getFeeds().then((result)=>{
    //   this.setState({
    //       showLoading:false,
    //       feeds:result.data.data
    //   })
    // })
  }
  
  render() {
    return(
      <div>
        <Header auth={this.state.isAuthenticated}/>
        <div className="add-question">
          <form>
          <h1>Add Question</h1>
            <label>Question Title</label>
            <input type="text" id="title" name="title" placeholder="Your Question Statement"/>
            <label>Category</label>
            <input type="text" id="category" name="category" placeholder="Your Question Category"/>
            <input type="submit" value="Submit"/>
            <div className="isa_info">
                Question Added Successfully.
            </div>
          </form>
        </div>
      </div>
    )
  }
}

export default AddQuestion;
