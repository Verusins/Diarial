<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="Title" prop="title" required>
        <el-input v-model="form.title" />
      </el-form-item>
      <el-form-item label="Weather" prop="weather" required>
        <el-select v-model="form.weather" placeholder="today's weather">
          <el-option label="Sunny" value="sunny" />
          <el-option label="Cloud" value="cloud" />
          <el-option label="Windy" value="windy" />
          <el-option label="Rain" value="rain" />
          <el-option label="Heavy Rain" value="heavy rain" />
          <el-option label="Snow" value="snow" />
          <el-option label="Heavy Snow" value="heavy snow" />
          <el-option label="Lightning" value="lightning" />
          <el-option label="Storm" value="storm" />
          <el-option label="Other" value="other" />
        </el-select>
      </el-form-item>
      <el-form-item label="Location">
        <el-input v-model="form.location" placeholder="where are you now" />
      </el-form-item>
      <el-form-item label="Keep it Private">
        <el-switch v-model="form.private" />
      </el-form-item>
      <el-form-item label="Content" required>
        <el-input v-model="form.content" type="textarea" rows="15" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">Submit</el-button>
        <el-button @click="onCancel">Cancel</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import diaryApi from '@/api/diary.js'
export default {
  data() {
    return {
      form: {
        title: '',
        weather: '',
        location: '',
        // private: false,
        content: ''
      }
    }
  },
  methods: {
    onSubmit(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.$message('Success!')
          console.log(this.form)
          diaryApi.submitDiary(this.form)
        } else {
          this.$message({
            message: 'Not Enough Info!',
            type: 'warning'
          })
        }
      })
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

