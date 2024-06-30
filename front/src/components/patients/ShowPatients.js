import Axios from "../../apis/Axios"
import { useNavigate } from "react-router-dom"
import { Button, Col, Row } from "react-bootstrap"
import { useCallback, useEffect, useState } from "react";
import { Role } from "../../services/auth";



const ShowPatients=(props)=>{

  //415 gresku RESAVAJU HEADERI I DATA
    const navigate= useNavigate();
    const [appointments, setAppointments] = useState([]);
    const deleteAppointment = (patientId) => {
            Axios.delete("/pregledi/" + patientId,{
             Headers:{'Content-Type': 'application/json'
            },
            data: {}})
            .then(res => {
               
                console.log(res);
                alert("Appointment was deleted was deleted")
                 window.location.reload();
              
            })
            .catch(error => {
                console.log(error);
                alert('The date is too late to delete an appointment!');
            })

    }



      const bookAppointment = (patientId) => {

            navigate("/appointment/"+patientId);
        
      
    }


        const deletePatient = (patientId) => {

   
        
            Axios.delete("/pacijenti/" + patientId)
            .then(res => {
               
                console.log(res);
                alert("Patient was deleted was deleted")
                 window.location.reload();
              
            })
            .catch(error => {
                console.log(error);
                alert('Error occured please try again!');
            })

    }




    





    const getPregledi=useCallback(()=>{
        Axios.get('/pregledi',{
         Headers:{'Content-Type': 'application/json'}
        ,data: {}}).
    then(res=>{
        console.log(res)
        setAppointments(res.data);

    }).catch(err=>{
        console.log(err);
    })

},[])

         useEffect(()=>{
      getPregledi()
    },[])


  

    function SubmitButton(patientId){

    
     let myNumber=0;
      for(let it in appointments){
        //console.log(appointments[it].pacijentId)
          if(appointments[it].pacijentId===patientId){
              myNumber= 10;
          }
      }

      console.log(myNumber);

    if (myNumber===0){
      return  <td> <Button variant="dark" type="button" onClick={()=>bookAppointment(patientId)}>Book appointment</Button></td>
    } else {
      return <td> <Button variant="danger" type="button"  onClick={()=>deleteAppointment(patientId)}  >Cancel appointment</Button></td> 
    };
  };
    


   

  return (
           <tr>
            <td>{props.patient.ime}</td>
            <td>{props.patient.prezime}</td>
            <td>{props.patient.datumRodjenja}</td>
              <td>{props.patient.mesto}</td>
            <td>{props.patient.pol}</td>
            <td>{props.patient.doktorIme}</td>
            <td>{props.patient.lbo}</td>
            {Role()=='isUser'&& 
                 SubmitButton(props.patient.id) }
            {Role()=='isAdmin'&&
           <td><Button className="btn btn-danger" onClick={() => deletePatient(props.patient.id)}>Delete Patient</Button></td>
            }
        </tr>
    )





















}


export default ShowPatients;