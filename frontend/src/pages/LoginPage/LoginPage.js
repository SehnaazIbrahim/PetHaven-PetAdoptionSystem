import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../../services/userService';
import './LoginPage.css';

const LoginPage = () => {
  const [data, setData] = useState({ email: '', password: '' });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await login(data);
      localStorage.setItem('token', res.data.token);
      localStorage.setItem('role', res.data.role);
      alert('Login successful!');
      if (res.data.role === 'ADMIN') navigate('/admin-dashboard');
      else navigate('/user-dashboard');
    } catch (err) {
      alert('Invalid credentials');
    }
  };

  return (
    <div className="login-page">
      <form className="login-form" onSubmit={handleLogin}>
        <h2>Login</h2>
        <input
          type="email"
          name="email"
          placeholder="Email"
          required
          value={data.email}
          onChange={handleChange}
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          required
          value={data.password}
          onChange={handleChange}
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;
