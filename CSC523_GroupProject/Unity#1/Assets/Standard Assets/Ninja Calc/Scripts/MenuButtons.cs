using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class MenuButtons : MonoBehaviour
{
    public AudioSource audio;
    public Animator ShowCredit;
    public Text m_score;
    private int score;
   void Start()
    {
        audio = Camera.main.GetComponent<AudioSource>();
        score = PlayerPrefs.GetInt("score", score);
        m_score.text = "You scored: " + score.ToString() + "!!!";
    }

    void FixedUpdate()
    {
        if(Input.GetKeyDown(KeyCode.M) && audio.isPlaying)
        {
            audio.Stop();
        }
        else if (Input.GetKeyDown(KeyCode.M) && !audio.isPlaying)
        {
            audio.Play();
        }

    }

    public void toggleMusic()
    {
        if(audio.isPlaying)
        {
            audio.Stop();
        }
        else
        {
            audio.Play();
        }
    }

    public void ReturnToMain()
    {
        PlayerPrefs.SetInt("health", 100);
        PlayerPrefs.SetInt("score", 0);
        SceneManager.LoadScene("OpenScene");
    }

   public void showCredits()
    {
        ShowCredit.SetBool("IsShowing", true);

    }
    public void closeCredits()
    {
        ShowCredit.SetBool("IsShowing", false);
    }
    public void StartPlaying()
    {
        PlayerPrefs.SetInt("health", 100);
        PlayerPrefs.SetInt("score", 0);
        SceneManager.LoadScene("Level_0");
    }
    public void TutorialScene()
    {
        SceneManager.LoadScene("TutorialScene");
    }

}
