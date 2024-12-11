import React, { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";

function App() {
  const [formData, setFormData] = useState({
    totalTickets: "",
    maxCapacity: "",
    ticketReleaseRate: "",
    customerRetrievalRate: "",
    eventName: "",
  });

  const [responseData, setResponseData] = useState(null);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch("http://localhost:8081/api/configuration/save", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      const data = await response.json();
      setResponseData(data);
    } catch (error) {
      console.error("Error submitting data:", error);
    }
  };

  const handleShowConfigs = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch("http://localhost:8081/api/configuration/", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      });
      const data = await response.json();
      setResponseData(data);
    } catch (error) {
      console.error("Error submitting data:", error);
    }
  };

  return (
    <div style={{ padding: "20px", textAlign: "center" }}>
      <header>
        <h1>WELCOME TO TICKETING SYSTEM</h1>
      </header>

      <main>
        <form onSubmit={handleSubmit} style={{ maxWidth: "400px", margin: "0 auto" }}>
          <div className="form">
            <label>
              <b>Enter The Total Number of Tickets</b>
              <input
                type="number"
                name="totalTickets"
                value={formData.totalTickets}
                onChange={handleChange}
                placeholder="Enter Number"
                required
              />
            </label>
            <br />

            <label>
              <b>Enter The Maximum Ticket Capacity</b>
              <input
                type="number"
                name="maxCapacity"
                value={formData.maxCapacity}
                onChange={handleChange}
                placeholder="Enter Number"
                required
              />
            </label>
            <br />

            <label>
              <b>Enter The Ticket Release Rate (In Milliseconds)</b>
              <input
                type="number"
                name="ticketReleaseRate"
                value={formData.ticketReleaseRate}
                onChange={handleChange}
                placeholder="Enter Number"
                required
              />
            </label>
            <br />

            <label>
              <b>Enter The Customer Retrieval Rate (In Milliseconds)</b>
              <input
                type="number"
                name="customerRetrievalRate"
                value={formData.customerRetrievalRate}
                onChange={handleChange}
                placeholder="Enter Number"
                required
              />
            </label>
            <br />

            <label>
              <b>Enter The Event Name</b>
              <input
                type="text"
                name="eventName"
                value={formData.eventName}
                onChange={handleChange}
                placeholder="Enter Event Name"
                required
              />
            </label>
          </div>
          <br />

          <div>
            <button type="submit">
              <b>SUBMIT</b>
            </button>
          </div>
          <br></br>
          <div>
            <button onClick={handleShowConfigs}>
              <b>SHOW CONFIGS</b>
            </button>
          </div>
        {responseData && (
          <div style={{ marginTop: "20px" }}>
            <h2>Response:</h2>
            <h3>Total Tickets: {responseData.totalTickets}</h3>
            <h3>Max Capacity: {responseData.maxCapacity}</h3>
            <h3>Ticket Release Rate: {responseData.ticketReleaseRate}</h3>
            <h3>Customer Retrieval Rate: {responseData.customerRetrievalRate}</h3>
            <h3>Event Name: {responseData.eventName}</h3>
          </div>
        )}
        </form>
      </main>
    </div>
  );
}

export default App;