import React, { Component } from "react";
import { Col, Spinner,Form, FormGroup, Label, Input } from 'reactstrap';
import { RegisterAPI } from "../../API/questions";
import {Header} from "../header/Header";
import 'bootstrap/dist/css/bootstrap.css';
class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      clickSubmit: true,
      showError: false,
      errorMsg: "",
      email:"",
      showLoading:false,
      isAuthenticated:false
    };
    this.handleUserInput = this.handleUserInput.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  componentDidMount(){
    let token=localStorage.getItem('Authorization');
    if(token){
        this.props.history.push("/");
    }
  }
  validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

  handleUserInput(e) {
    const name = e.target.name;
    const value = e.target.value;
    if(name==="email" && !this.validateEmail(value)){
        this.setState({ 
            showError: true,
            errorMsg:"Email Entered is Invalid"
         });
    }else{
        this.setState({ [name]: value });
        this.setState({ showError: false });
    }
  }

  handleSubmit=event=> {
    event.preventDefault();
    if (!this.state.showError && this.state.email) {
      this.setState({ showLoading: true}); 
      RegisterAPI(this.state.email)
        .then((result)=>{
          console.log("result",result);
            localStorage.setItem('userid', result.data.id);
            localStorage.setItem('email',result.data.email);
            this.setState({
              showError:false,
              showLoading: false
            })
            this.props.history.push("/questions");
        }).catch((error)=>{
            if (error.response) {
                this.setState({
                    showError:true,
                    errorMsg:error.response.data.error,
                    showLoading: false
                })
            }
           
        })  
    }
  }
  renderButton() {
    if (!this.state.showLoading) {
      return (
        <FormGroup row>
            <Col md={{ size: 6, offset: 3 }}>
            <button 
            className="btn btn-primary"
            name="action"
            onClick={this.handleSubmit}
            >
            Register</button>
            </Col>
        </FormGroup> 
      );
    } else {
      return (
        <FormGroup row>
            <Col md={{ size: 6, offset: 3 }}>
                <Spinner style={{ width: '3rem', height: '3rem' }} />
            </Col>
        </FormGroup>  
      );
    }
  }

  render() {
    return (
        <div style={{marginTop:100}}>
            {/* <Header auth={this.state.isAuthenticated}/> */}
           <Form>
                <FormGroup row className="form-group required">
                    <Col md={{ size: 6, offset: 3 }}>
                        <Label className="col-md-2 control-label" for="exampleEmail" sm={2}>Email</Label>
                        <Input type="email" name="email" id="email" placeholder="Enter Email Address" onChange={this.handleUserInput} required/>
                    </Col>
                </FormGroup>
            {this.state.showError && (
            <div className="formError">
              <FormGroup row>
                  <Col md={{ size: 6, offset: 3 }}>
                    {this.state.errorMsg}
                  </Col>
              </FormGroup>
            </div>
          )}
            {this.renderButton()}
             </Form>
        </div>);
  }
}

export default Register;