﻿using UnityEngine.Audio;
using System;
using UnityEngine;

public class AudioManager : MonoBehaviour
{
	public Sounds[] sounds;
	// Start is called before the first frame update
	void Awake()
	{
		foreach (Sounds s in sounds)
		{
			s.source = gameObject.AddComponent<AudioSource>();
			s.source.clip = s.clip;

			s.source.volume = s.volume;
			s.source.pitch = s.pitch;

			s.source.loop = s.loop;

		}
	}


	void start()
	{
		Play("Theme");
	}
	public void Play(string name)
	{
		Sounds s = Array.Find(sounds, sound => sound.name == name);
		if (s == null)
		{
			Debug.LogWarning("Sound:" + name + " not found!");
			return;
		}
		s.source.Play();
	}
}
