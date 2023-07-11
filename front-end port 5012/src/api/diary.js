import request from '@/utils/request'

export default {
  submitDiary(diary) {
    return request({
      url: '/diaries/upload',
      method: 'post',
      data: diary
    })
  },

  getDiaryList() {
    return request({
      url: '/diaries/all',
      method: 'get',
      data: null
    })
  }
}
