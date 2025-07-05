import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Navbar.css';

const Navbar = () => {
  const navigate = useNavigate();
  const isLoggedIn = localStorage.getItem("token");

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <nav className="navbar">
      <div className="logo">🐾 PetAdopt</div>
      <div className="nav-links">
        <Link to="/">Home</Link>
        <Link to="/about">About</Link>
        <Link to="/contact">Contact</Link>

        {isLoggedIn ? (
          <>
            <Link to={localStorage.getItem("role") === "ADMIN" ? "/admin-dashboard" : "/user-dashboard"}>
              Dashboard
            </Link>
            <button className="logout-btn" onClick={logout}>Logout</button>
          </>
        ) : (
          <>
            <Link to="/login">Login</Link>
            <Link to="/signup">Signup</Link>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
