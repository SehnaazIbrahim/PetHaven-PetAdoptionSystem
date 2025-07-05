import React from 'react';
import './ContactPage.css';

const ContactPage = () => {
  return (
    <div className="contact-container">
      <h2>Contact Us</h2>
      <p>We’d love to hear from you! Reach out with questions, suggestions, or anything else 🐾</p>

      <div className="contact-info">
        <p><strong>Email:</strong> support@petadopt.com</p>
        <p><strong>Phone:</strong> +91 98765 43210</p>
        <p><strong>Address:</strong> 123 Pet Street, Coimbatore, Tamil Nadu - 641001</p>
      </div>

      <div className="contact-form">
        <h3>Send a Message</h3>
        <form onSubmit={(e) => {
          e.preventDefault();
          alert("Thank you for your message! We'll get back to you shortly.");
          e.target.reset();
        }}>
          <input type="text" placeholder="Your Name" required />
          <input type="email" placeholder="Your Email" required />
          <textarea rows="5" placeholder="Your Message" required />
          <button type="submit">Send Message</button>
        </form>
      </div>
    </div>
  );
};

export default ContactPage;

