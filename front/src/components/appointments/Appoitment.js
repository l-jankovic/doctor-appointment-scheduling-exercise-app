import Axios from "../../apis/Axios"
import {  useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { Row, Col, Form, Button } from "react-bootstrap";


const Appointment=()=>{


    const routeParams = useParams()
    const patientId = routeParams.id;

    const [selectedValue, setSelectedValue] = useState(30);

const handleRadioChange = (event) => {
    setSelectedValue(parseInt(event.target.value)); // Parsiranje vrednosti u broj ako je potrebno
    console.log(parseInt(event.target.value))
  };


    const[createdAppointment,setCreatedAppointment]=useState({
        dateap:null,
        timeap:null,
        simptoms:"",
        doctorId:"",


    });
      const [doctors,setDoctors]=useState([]);

    var navigate = useNavigate();

    
    const getDoctors=()=>{
    Axios.get('/doktori').
    then(res=>{
        console.log(res)
        setDoctors(res.data);

    }).catch(err=>{
        console.log(err);
    })

}

 


     useEffect(()=>{
      getDoctors();
    },[])

 
 


    const datumIVreme = (createdAppointment.date && createdAppointment.time)
        ?createdAppointment.date  + "T" + createdAppointment.time
        : null;


    const addPatient = () =>{

     console.log(createdAppointment.dateap +" VREME PREGLEFDA");
   
        const dto={
                   pacijentId:patientId,
                   pocetakPregleda:createdAppointment.dateap  + "T" + createdAppointment.timeap,
                   trajanjePregleda:selectedValue,
                   simptomi:createdAppointment.simptoms,
                   doktorId:createdAppointment.doctorId,


        }

        

        console.log(dto.pocetakPregleda +" VREME PREGLEFDA");

         console.log(dto.pacijentId +" id");

        Axios.post('/pregledi',dto).then(res =>{

        console.log(res);
        navigate("/patients");

    }).catch(err =>{
        console.error("Status kod:", err.response.status);
        console.error("Poruka:", err.response.data);
         console.error("AxiosError:", err);

        alert("Doslo je do greske");
    })

}

  const onValueChange=(e)=>{
        const {name,value}=e.target;




        setCreatedAppointment({ ...createdAppointment,[name]:value});
        console.log(e.target.value);
    }  


    

         return (
            
            <Row >
                <Col></Col>
                <Col xs="12" sm="10" md="8">
          
                    <Form>
                    <Row>
                        <Col>
                        <Form.Group>
                            <Form.Label>Date</Form.Label>
                            <Form.Control id="dateap" name="dateap" type="date" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                        </Col>
                        <Col>
                        <Form.Group>
                            <Form.Label>Time</Form.Label>
                            <Form.Control id="timeap" name="timeap" type="time" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                        </Col>
                        </Row>
                        <Form.Group>
                            <Form.Label>Doctor</Form.Label>
                            <Form.Control as='select' id="doctorId" name="doctorId" onChange={(e) => onValueChange(e)} >
                                 <option value={""}>Choose your doctor</option>
                                       {doctors.map((doctor, i) => (
                                        <option key={doctor.id} value={doctor.id}>{doctor.ime}</option>
                                    ))}
                            </Form.Control>
                             <br />
                        </Form.Group>
                        <Row>
                           <p>Appointment duration</p>
                        <Col>
                        <Form.Group>
                         
                            <Form.Label> 1 hour </Form.Label>

                                <input type="radio"  name="durationHour" value={60} onChange={handleRadioChange}  checked={selectedValue === 60}></input>
                             <br />
                        </Form.Group>
                        </Col>

                        <Col>
                        <Form.Group>
                            <Form.Label>30 minutes  </Form.Label>

                                <input type="radio"  name="durationMintes" value={30}  onChange={handleRadioChange} checked={selectedValue === 30} ></input>
                             <br />
                        </Form.Group>
                                </Col>
                                
                        </Row>
                          <Form.Group>
                            <Form.Label>Simptoms</Form.Label>
                            <Form.Control id="simptoms" as='textarea' name="simptoms" onChange={(e) => onValueChange(e)} /> <br />
                        </Form.Group>
                    <Row>
                    <Button variant="success" size="lg" className=" btn-block" onClick={addPatient}>Add</Button>
                    </Row>
                    </Form>
                </Col>
                <Col></Col>
          
            </Row>
    )   
}


export default Appointment; 
