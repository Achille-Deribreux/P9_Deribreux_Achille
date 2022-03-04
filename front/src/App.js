import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from "./containers/Home";
import { Routes, Route } from "react-router-dom";
import Patients from "./containers/Patients";
import Profile from "./containers/Profile";
import Note from "./containers/Note";
import Notes from "./containers/Notes";
import EditProfile from "./containers/EditProfile";
import AddNote from "./containers/AddNote";
import EditNote from "./containers/EditNote";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/patients" element={<Patients />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/note" element={<Note />} />
        <Route path="/notes" element={<Notes />} />
        <Route path="/editProfile" element={<EditProfile />} />
        <Route path="/addNote" element={<AddNote />} />
        <Route path="/editNote" element={<EditNote />} />
      </Routes>
    </div>
  );
}

export default App;
