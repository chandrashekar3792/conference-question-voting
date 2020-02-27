import React, { Component } from 'react';
import "./Style.css";
export class Header extends Component {
	constructor(props) {
		super(props);

		this.state = {
		 isOpen: false,
		 token:localStorage.getItem("Authorization")
		};
	}


	render() {
		return (
			<header className="navbar">
				<div className="navbar-header">
					<div className="navbar-top">
						<a className="navbar-anchor" href="/">
							<span className="navbar-span">CREAZA CONFERENCE VOTING</span>
						</a>
						<nav className="nav-header">
							<a className="nav-item" href="/questions">View Questions</a>
							<a className="nav-item" href="/questions/add">Add Question</a>
							<a className="nav-item" href="/questions/vote">Vote Question</a>
						</nav>
					</div>
				</div>
			</header>
		);
  }
}