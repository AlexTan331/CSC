using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
public class Tutorial : MonoBehaviour
{
    public AudioSource audio;

    public void toggleMusic()
    {
        if (audio.isPlaying)
        {
            audio.Stop();
        }
        else
        {
            audio.Play();
        }
    }
    public void ReturntoMain()
    {
        SceneManager.LoadScene("OpenScene");
    }

    void Update()
    {
        if (Input.GetKeyDown(KeyCode.R))
        {
            SceneManager.LoadScene("OpenScene");
        }
        if (Input.GetKeyDown(KeyCode.M))
        {
            if (audio.isPlaying)
            {
                audio.Stop();
            }
            else
            {
                audio.Play();
            }
        }
    }

    /* public Camera[] cameras;
     private int currentCameraIndex;

     // Use this for initialization
     void Start()
     {
         currentCameraIndex = 0;

         //Turn all cameras off, except the first default one
         for (int i = 1; i < cameras.Length; i++)
         {
             cameras[i].gameObject.SetActive(false);
         }

         //If any cameras were added to the controller, enable the first one
         if (cameras.Length > 0)
         {
             cameras[0].gameObject.SetActive(true);       
         }
     }

     // Update is called once per frame
     void Update()
     {
         //If the c button is pressed, switch to the next camera
         //Set the camera at the current index to inactive, and set the next one in the array to active
         //When we reach the end of the camera array, move back to the beginning or the array.
         if (Input.GetKeyDown(KeyCode.C))
         {
             currentCameraIndex++;
             if (currentCameraIndex < cameras.Length)
             {
                 cameras[currentCameraIndex - 1].gameObject.SetActive(false);
                 cameras[currentCameraIndex].gameObject.SetActive(true);
             }
             else
             {
                 cameras[currentCameraIndex - 1].gameObject.SetActive(false);
                 currentCameraIndex = 0;
                 cameras[currentCameraIndex].gameObject.SetActive(true);
                 SceneManager.LoadScene("OpenScene");
             }
         }
     }
     */




}